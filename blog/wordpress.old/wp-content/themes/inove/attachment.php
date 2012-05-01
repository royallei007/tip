<?php
/**
 * @package INove
 */
get_header(); ?>
<?php $options = get_option('inove_options'); ?>

<?php if (have_posts()) : the_post(); ?>

	<div id="postpath">
		<a title="<?php esc_attr_e( 'Go to homepage', 'inove' ); ?>" href="<?php echo home_url( '/' ); ?>"><?php _e('Home', 'inove'); ?></a>
		 &gt; <a href="<?php echo get_permalink($post->post_parent); ?>"><?php echo get_the_title($post->post_parent); ?></a>
		 &gt; <?php the_title(); ?>
	</div>

	<div class="post" id="post-<?php the_ID(); ?>">
		<h2><?php the_title(); ?></h2>
		<div class="info">
			<span class="date"><?php the_time(get_option('date_format')); ?></span>
			<?php if ($options['author']) : ?><span class="author"><?php the_author_posts_link(); ?></span><?php endif; ?>
			<?php edit_post_link(__('Edit', 'inove'), '<span class="editpost">', '</span>'); ?>
			<?php if ( have_comments() || comments_open() ) : ?>
				<span class="addcomment"><a href="#respond"><?php _e('Leave a comment', 'inove'); ?></a></span>
				<span class="comments"><a href="#comments"><?php _e('Go to comments', 'inove'); ?></a></span>
			<?php endif; ?>
			<div class="fixed"></div>
		</div>
		<div class="content attachment">
			<a href="<?php echo wp_get_attachment_url(); ?>" title="<?php echo esc_attr( get_the_title() ); ?>" rel="attachment"><?php the_title(); ?></a>
		</div>
		<div class="under">
		</div>
	</div>

	<!-- related posts START -->
	<?php
		// when related posts with title
		if(function_exists('wp23_related_posts')) {
			echo '<div id="related_posts">';
			wp23_related_posts();
			echo '</div>';
			echo '<div class="fixed"></div>';
		}
		/*
		// when related posts without title
		if(function_exists('wp23_related_posts')) {
			echo '<div class="boxcaption">';
			echo '<h3>Related Posts</h3>';
			echo '</div>';
			echo '<div id="related_posts" class="box">';
			wp23_related_posts();
			echo '</div>';
			echo '<div class="fixed"></div>';
		}
		*/
	?>
	<!-- related posts END -->

	<?php comments_template('', true); ?>

	<div id="postnavi">
		<span class="prev"><?php next_post_link('%link') ?></span>
		<span class="next"><?php previous_post_link('%link') ?></span>
		<div class="fixed"></div>
	</div>

<?php else : ?>
	<div class="errorbox">
		<?php _e('Sorry, no posts matched your criteria.', 'inove'); ?>
	</div>
<?php endif; ?>

<?php get_footer(); ?>
