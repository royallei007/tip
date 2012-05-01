<?php
/**
 * @package WordPress
 * @subpackage Neutra
 */
$content_width = 760; // allow for wider layout
get_header(); ?>

<div id="page">

	<div id="left" class="image full">

		<?php if ( have_posts() ) : ?>
		<?php while ( have_posts() ) : the_post(); ?>
			<div <?php post_class('post'); ?> id="post-<?php the_ID(); ?>">
				<h2 class="title"><a href="<?php echo get_permalink( $post->post_parent ); ?>" rev="attachment"><?php echo get_the_title( $post->post_parent ); ?></a> &raquo; <?php the_title(); ?></h2>
				<div class="postcontent">
					<p class="attachment"><a href="<?php echo wp_get_attachment_url( $post->ID ); ?>"><?php echo wp_get_attachment_image( $post->ID, array( $content_width, $content_width ) ); ?></a></p>
					<?php the_content( 'Read more&#8230;' ); ?>
					<div class="navigation image full">
						<div class="alignleft"><?php previous_image_link(); ?></div>
						<div class="alignright"><?php next_image_link(); ?></div>
					</div>
					<p class="edit-post"><?php edit_post_link( __( '(Edit this entry)' ) ); ?></p>
					<div class="postmetadata">
						<div class="floatright">
							<p><span class="date"><?php the_time( get_option( 'date_format' ) ); ?></span></p>
							<p><span class="comments"><?php comments_popup_link( __( 'Leave a comment' ), __( '1 Comment' ), __( '% Comments' ) ) ?></span></p>
						</div><!-- /floatright -->
					</div><!-- /postmetadata -->

				</div><!-- /postcontent -->
			</div><!-- /post -->

			<?php comments_template('', true); ?>

			<?php endwhile; ?>
			<?php else : ?>

			<div class="post">
				<h2 class="title">I'm sorry, I couldn't find the image!</h2>
				<div class="postcontent">
					<p>Don't worry, you can always search the <strong>archives</strong> or browse the <strong>categories</strong>.</p>
				</div>
			</div><!-- /post -->

	<?php endif; ?>

	</div><!-- /left -->

</div><!-- /page -->

<?php get_footer(); ?>
