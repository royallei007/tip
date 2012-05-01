<?php get_header(); ?>
<?php get_sidebar(); ?>
<div id="content">

<?php
if (have_posts()) {
	while(have_posts()) {
		the_post();
?>
<br />
<div <?php post_class(); ?>>
	<a class="title" href="<?php the_permalink() ?>" style="text-decoration:none;" rel="bookmark" title="<?php echo esc_attr( sprintf( __( 'Permanent Link: %s', 'benevolence' ), the_title_attribute( 'echo=0' ) ) ); ?>"><?php the_title(); ?></a>
	<?php if( !is_page() ) { ?>
		<div class="cite"><?php the_time(get_option('date_format')) ?>, <?php the_time() ?> <?php edit_post_link(__('Edit this', 'benevolence'), ' | ', ''); ?><br />
		<?php _e('Filed under:', 'benevolence'); ?> <?php the_category(', ') ?> <?php the_tags( ' | ' . __( 'Tags' ) . ': ', ', ', ''); ?></div>
	<?php } else { ?>
		<div class="cite"><?php edit_post_link(__('Edit this', 'benevolence')); ?></div>
	<?php } ?>
		<?php the_content(__('(more...)', 'benevolence')); ?>
	<div class="commentPos"><?php wp_link_pages(); ?> <?php if ( ! is_page() ) : ?><?php comments_popup_link(__('Leave a Comment', 'benevolence'), __('1 Comment', 'benevolence'), __('% Comments', 'benevolence')); ?><?php endif; ?></div>
	<br />
	
    <div class="sep"></div>

<?php comments_template(); // Get wp-comments.php template ?>
</div>
<?php } // closes printing entries with excluded cats ?>

<?php } else { ?>
<?php _e('Sorry, no posts matched your criteria.', 'benevolence'); ?>
<?php } ?>

 <div class="left"><?php next_posts_link( '&laquo; Older Posts' ); ?></div>
 <div class="right"><?php previous_posts_link( 'Newer Posts &raquo;' ); ?></div>

<br /><br />

</div>

<?php get_footer(); ?>
